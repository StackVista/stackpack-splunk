### Prerequisites

* A running Splunk instance.
* A Splunk user account with access to Splunk saved searches. The user should have the capability `search` to dispatch and read Splunk saved searches.
* A compatible StackState Agent installed on a machine that can connect to both Splunk and StackState:
  - Metrics and events data: The [API-Integration Agent](/stackpacks/integrations/api-integration.md)
  - Health data: [StackState Agent V2](/stackpacks/integrations/agent.md)
  - Topology data: [StackState Agent V2](/stackpacks/integrations/agent.md) or the [API-Integration Agent](/stackpacks/integrations/api-integration.md)

## Integration details

### Data retrieved

#### Events

When the Splunk events Agent check is configured, events will be retrieved from the configured Splunk saved searches. Events retrieved from splunk are available in StackState as a log telemetry stream in the `stackstate-generic-events` data source. This can be [mapped to associated components](/use/health-state-and-event-notifications/add-telemetry-to-element.md).

For details on how to configure the events retrieved, see the [Splunk events check configuration](/stackpacks/integrations/splunk/splunk_events.md).

#### Metrics

When the Splunk metrics Agent check is configured, metrics will be retrieved from the configured Splunk saved searches. One metric can be retrieved from each saved search. Metrics retrieved from Splunk are available in StackState as a metrics telemetry stream in the `stackstate-metrics` data source. This can be [mapped to associated components](/use/health-state-and-event-notifications/add-telemetry-to-element.md).

For details on how to configure the metrics retrieved, see the [Splunk metrics check configuration](/stackpacks/integrations/splunk/splunk_metrics.md).

#### Topology

When the Splunk StackPack is installed and a Splunk topology Agent check is configured, topology will be retrieved from the configured Splunk saved searches. The check that you should configure depends on the StackState Agent that you will use to retrieve topology data. The Splunk topology V1 check uses the API-Integration Agent to retrieve data from Splunk, while the Splunk topology V2 check uses StackState Agent V2.

For details on how to configure the components and relations retrieved, see:

- The [Splunk topology V2 check configuration - StackState Agent V2](/stackpacks/integrations/splunk/splunk_topology.md)
- The [Splunk topology V1 check configuration - API-Integration Agent](/stackpacks/integrations/splunk/splunk_topology.md)

If you have an existing Splunk topology integration configured to use the API-Integration Agent and would like to upgrade to use StackState Agent V2, refer to the [Splunk topology check upgrade instructions](splunk_topology_upgrade_v1_to_v2.md).

#### Health

When the Splunk health Agent check is configured, health check states will be retrieved from the configured Splunk saved searches. Retrieved health check states are mapped to the associated components and relations in StackState.

For details on how to configure the health retrieved, see the [Splunk health check configuration](/stackpacks/integrations/splunk/splunk_health.md).

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

For further details, see the [Splunk API documentation \(docs.splunk.com\)](https://docs.splunk.com/Documentation/Splunk/8.1.3/RESTREF/RESTprolog).

### Open source

The Splunk Topology StackPack and the Agent checks for Splunk events, metrics and topology are open source and available on GitHub at the links below:

* [Splunk StackPack \(github.com\)](https://github.com/StackVista/stackpack-splunk)
* [Splunk topology V1 check \(github.com\)](https://github.com/StackVista/sts-agent-integrations-core/tree/master/splunk_topology)
* [Splunk topology V2 check \(github.com\)](https://github.com/StackVista/stackstate-agent-integrations/tree/master/splunk_topology)
* [Splunk metrics check \(github.com\)](https://github.com/StackVista/sts-agent-integrations-core/tree/master/splunk_metric)
* [Splunk events check \(github.com\)](https://github.com/StackVista/sts-agent-integrations-core/tree/master/splunk_event)
* [Splunk health check \(github.com\)](https://github.com/StackVista/stackstate-agent-integrations/tree/master/splunk_health)
