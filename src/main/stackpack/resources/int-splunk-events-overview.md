## Overview

The StackState Splunk events integration collects events from Splunk by executing Splunk saved searches from [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/). In order to receive Splunk events data in StackState, configuration needs to be added to both Splunk and StackState Agent V2:

* In Splunk there should be at least one saved search that generates the events data you want to retrieve.
* In StackState Agent V2, a Splunk events check should be configured to connect to your Splunk instance and execute the relevant Splunk saved searches.

The Splunk events check on StackState Agent V2 will execute all configured Splunk saved searches periodically. Data will be requested from the last received event timestamp up until now. 

## Splunk saved search

### Fields used

StackState Agent V2 executes the Splunk saved searches configured in the Splunk events Agent check configuration file and pushes retrieved data to StackState as a telemetry stream. The following fields from the results of a saved search are sent to StackState:

| Field | Type | Required? | Description |
| :--- | :--- | :--- |
| **\_time** | long | ✅ | The data collection timestamp, in milliseconds since epoch. |
| **event\_type** | string | - | Event type, for example `server_created`. |
| **msg\_title** | string | - | Message title. |
| **msg\_text** | string | - | Message text. |
| **source\_type\_name** | string | - | Source type name. |
| All other fields | - | - | [Splunk default fields \(docs.splunk.com\)](https://l.stackstate.com/ui-splunk-default-fields) other than `_time` will be filtered out of the result.<br />Any other fields present in the result will be mapped to tags in the format `field`:`value`. |

### Example Splunk query

```text
index=monitor alias_hostname=*
| eval status = upper(status)
| search status=CRITICAL OR status=error OR status=warning OR status=OK
| table _time hostname status description
```

The example Splunk saved search above would result in the following event data in StackState:

| Field | Data |
| :--- | :--- |
| **\_time** | Splunk `_time` field. |
| **event\_type** | - |
| **msg\_title** | - |
| **msg\_text** | - |
| **source\_type\_name** | - |
| **tags** | `hostname:<hostname>`<br />`status:<status>`<br />`description:<description>` |

## Agent check

### Configure the Splunk events check

To enable the Splunk events integration and begin collecting events data from your Splunk instance, the Splunk events check must be configured on StackState Agent V1. The check configuration provides all details required for the Agent to connect to your Splunk instance and execute a Splunk saved search.

See the example Splunk events Agent check configuration file: [splunk_event/conf.yaml.example \(github.com\)](https://l.stackstate.com/ui-splunk-events-v2-check-example)

To configure the Splunk events Agent check:

1. Edit the StackState Agent V2 configuration file `/etc/stackstate-agent/conf.d/splunk_event.d/conf.yaml`.
2. Under **instances**, add details of your Splunk instance:
   * **url** - The URL of your Splunk instance.
   * **authentication** - How the Agent should authenticate with your Splunk instance. Choose either token-based (recommended) or basic authentication. For details, see [authentication configuration details](https://l.stackstate.com/ui-splunk-stackpack-authentication)).
   * **tags** - Optional. Can be used to apply specific tags to all reported events in StackState.
3. Under **saved_searches**, add details of each Splunk saved search that the check should execute: 
     * **name** - The name of the Splunk saved search to execute.
       * **match** - Regex used for selecting Splunk saved search queries. Default `"events.*"`.
       * **app** - The Splunk app in which the saved searches are located. Default `"search"`.
       * **request_timeout_seconds** - Default `10`.
       * **search_max_retry_count** - Default `5`.
       * **search_seconds_between_retries** - Default `1`.
       * **batch_size** - Default `1000`.
       * **initial_history_time_seconds** - Default `0`.
       * **max_restart_history_seconds** - Default `86400`.
       * **max_query_chunk_seconds** - Default `3600`.
       * **unique_key_fields** - The fields to use to uniquely identify a record (see below for details). Default `_bkt` and `_cd`.
       * **parameters** - Used in the Splunk API request. The default parameters provided make sure the Splunk saved search query refreshes. Default `force_dispatch: true` and `dispatch.now: true`.
5. More advanced options can be found in the [example configuration \(github.com\)](https://l.stackstate.com/ui-splunk-events-v2-check-example). 
4. Save the configuration file.
5. Restart StackState Agent V1 to apply the configuration changes.
6. Once the Agent has restarted, wait for the Agent to collect data and send it to StackState.
7. Events retrieved from splunk are available in StackState as a log telemetry stream in the `stackstate-generic-events` data source. This can be [mapped to associated components](https://l.stackstate.com/ui-splunk-add-telemetry-stream).

### Uniquely identify a record

To prevent sending duplicate events over multiple check runs, received saved search records must be uniquely identified for comparison. By default, a record is identified by the Splunk default fields `_bkt` and `_cd`. This behavior can be customized for each saved search by specifying `unique_key_fields` in the Splunk events Agent check configuration. Note that the specified `unique_key_fields` fields are mandatory fields for each record returned by the Splunk saved search. 

If it is not possible to uniquely identify a record by a combination of specific fields, the whole record can be used by setting `unique_key_fields: []` (an empty list).

### Disable the Agent check

To disable the Splunk events Agent check:

1. Remove or rename the Agent integration configuration file, for example:

   ```text
   mv /etc/stackstate-agent/conf.d/splunk_event.d/conf.yaml /etc/stackstate-agent/conf.d/splunk_event.d/conf.yaml.bak
   ```

2. Restart the StackState Agent\(s\) to apply the configuration changes.


## Splunk events in StackState

Events retrieved from splunk are available in StackState as a log telemetry stream in the `stackstate-generic-events` data source. This can be [mapped to associated components](https://l.stackstate.com/ui-splunk-add-telemetry-stream).