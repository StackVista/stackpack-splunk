### Prerequisites

To set up the Splunk integration you will need to have:

* A running Splunk instance.
* A Splunk user account with access to Splunk saved searches. The user should have the capability `search` to dispatch and read Splunk saved searches.
* A compatible StackState Agent installed on a machine that can connect to both Splunk and StackState:
  - Metrics data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/)
  - Events data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/)
  - Health data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/)
  - Topology data: [StackState Agent V2](/#/stackpacks/stackstate-agent-v2/)

For full details of the Splunk integration, including the data retrieved and available filters, see the [Splunk StackPack documentation \(docs.stackstate.com\)](https://l.stackstate.com/ui-splunk-stackpack-docs).
