### Prerequisites

* A running Splunk instance.
* A Splunk user account with access to Splunk saved searches. The user should have the capability `search` to dispatch and read Splunk saved searches.
* A compatible StackState Agent installed on a machine that can connect to both Splunk and StackState:
  - Metrics and events data: The [API-Integration Agent](/#/stackpacks/api-integration/)
  - Health data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/)
  - Topology data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/) or the [API-Integration Agent](/#/stackpacks/api-integration/)

## Integration details

### Data retrieved

#### Events

When the Splunk events Agent check is configured, events will be retrieved from the configured Splunk saved searches. Events retrieved from splunk are available in StackState as a log telemetry stream in the `stackstate-generic-events` data source. This can be [mapped to associated components](https://l.stackstate.com/ui-splunk-add-telemetry-stream).

For details on how to configure the events retrieved, see the [Splunk events check configuration](/#/stackpacks/splunk/int-splunk-events-overview.md).

#### Metrics

When the Splunk metrics Agent check is configured, metrics will be retrieved from the configured Splunk saved searches. One metric can be retrieved from each saved search. Metrics retrieved from Splunk are available in StackState as a metrics telemetry stream in the `stackstate-metrics` data source. This can be [mapped to associated components](https://l.stackstate.com/ui-splunk-add-telemetry-stream).

For details on how to configure the metrics retrieved, see the [Splunk metrics check configuration](/#/stackpacks/splunk/int-splunk-metrics-overview.md).

#### Topology

When the Splunk StackPack is installed and a Splunk topology Agent check is configured, topology will be retrieved from the configured Splunk saved searches. The check that you should configure depends on the StackState Agent that you will use to retrieve topology data. The Splunk topology V1 check uses the API-Integration Agent to retrieve data from Splunk, while the Splunk topology V2 check uses StackState Agent V2.

For details on how to configure the components and relations retrieved, see:

- The [Splunk topology V2 check configuration - StackState Agent V2](/#/stackpacks/splunk/int-splunk-topology-v2-overview.md)
- The [Splunk topology V1 check configuration - API-Integration Agent](/#/stackpacks/splunk/int-splunk-topology-v1-overview.md)

If you have an existing Splunk topology integration configured to use the API-Integration Agent and would like to upgrade to use StackState Agent V2, refer to the [Splunk topology check upgrade instructions](https://l.stackstate.com/ui-splunk-topology-upgrade-to-v2).

#### Health

When the Splunk health Agent check is configured, health check states will be retrieved from the configured Splunk saved searches. Retrieved health check states are mapped to the associated components and relations in StackState.

For details on how to configure the health retrieved, see the [Splunk health check configuration](/#/stackpacks/splunk/int-splunk-health-overview.md).

#### Traces

The StackState Splunk integration does not retrieve any trace data.

### REST API endpoints

The StackState API-Integration Agent connects to the Splunk API at the endpoints listed below. The same endpoints are used to retrieve events, metrics and topology data.

| Endpoint | Description |
|:--- |:--- |
| `/services/auth/login?output_mode=json` | Auth login |
| `/services/authorization/tokens?output_mode=json` | Create token |
| `/services/saved/searches?output_mode=json&count=-1` | List of saved searches |
| `/servicesNS/<user>/<app>/saved/searches/<saved_search_name>/dispatch` | Dispatch the saved search |
| `/services/search/jobs/<saved_search_id>/control` | Finalize the saved search |

For further details, see the [Splunk API documentation \(docs.splunk.com\)](https://l.stackstate.com/ui-splunk-api-docs).

### Open source

The Splunk Topology StackPack and the Agent checks for Splunk events, metrics and topology are open source and available on GitHub at the links below:

* [Splunk StackPack \(github.com\)](https://l.stackstate.com/ui-splunk-stackpack-github)
* [Splunk topology V1 check \(github.com\)](https://l.stackstate.com/ui-splunk-coptology-v1-check-github)
* [Splunk topology V2 check \(github.com\)](https://l.stackstate.com/ui-splunk-topology-v2-check-github)
* [Splunk metrics check \(github.com\)](https://l.stackstate.com/ui-splunk-metrics-check-github)
* [Splunk events check \(github.com\)](https://l.stackstate.com/ui-splunk-events-check-github)
* [Splunk health check \(github.com\)](https://l.stackstate.com/ui-splunk-health-check-github)
