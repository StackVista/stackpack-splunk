## Splunk Topology

Topology information can be read periodically from Splunk saved searches. One or more saved searches can be created for components and optionally one or more saved searches for relations. The expected format for component and relation saved searches are described below. 

A component or relation saved searches can either be global, in the search app, or in a specific Splunk app.

The API Integration Agent reads the saved searches periodically and sends collected topology data to StackState.  The API Integration Agent will dispatch the saved searches, read data when it's available, and send data to StackState. After data is read from the saved earch jobs, the Splunk saved search jobs are finalized.


### Prerequisites

* StackState API Integration Agent is installed, either next to StackState on the same instance/virtual machine or elsewhere. The API Integration Agent can be installed with the use of the API Integration StackPack.
* Splunk user account to access saved searches in a Splunk app, basic authentication or token based authentication is supported. This user need to have access to the app in where the saved searches are located. The user's capabilities need to include capability `search`, for dispatching and reading saved searches.

### Network requirements

* StackState API Integration Agent can reach Splunk's API over the network. The default Splunk API port is 8089/tcp.
* StackState Integration Agent can reach StackState over the network. The default StackState API port is 7077/tcp.


### Component saved search

A component saved search requires to output the following table columns.

| Column/field | Mandatory | Description  |
| ------ | --------- | ----------- |
| **id** | yes |  The component's unique identifier within the data source. |
| **type** | yes | The type of a component, e.g. VM, datastore, rack, etc. |
| **name** | no | The component's name. If omitted the `id` is used as the component's name. |
| **domain** | no | The StackState domain where the component should be visualized. If omitted, `Splunk` is used. |
| **layer** | no | The StackState layer in where the component should be visualized. If omitted, `Uncategorized` is used. |
| **environment** | no | The StackState environment in where the component should be visualized. If omitted, `Production` is used. |
| **labels** | no | The StackState labels that the component should have. This field can either be a multivalue field, a single String, or a comma separated String, e.g. "label1,key:value". |
| **identifiers** | no | The StackState identifiers the component should have for merging between synchronizations/topology data sources. This field can either by a multivalue field, a single String, or a comma separated String, e.g. "id1,fqdn2". |

Fields are case sensitive. Additional fields are added as meta data to the component.


Example;

```
| makeresults count=2
| streamstats count
| eval id = "my_component_identifier_" + count, type = "VM", name = "My component's name " + count 
| table id, type, name
```


### Relation saved search

An optional reation saved search requires to output the following table columns.

| Column/field | Mandatory | Description
| --- | --- | --- |
| **sourceId** | yes | The component’s identifier that is used to create a relation from. The identifier has to case sensitively match a component’s id in any of the added component saved searches. |
| **targetId** | yes | The component’s identifier that is used to create a relation to. The identifier has to case sensitively match a component’s id in any of the added component saved searches. |
| **type** | yes | The type of the relation, e.g. `uses`, `depends on` |

Fields are case sensitive. Additional fields are added as meta data to the relation.

Example;

```
| makeresults
| eval sourceId = "my_component_identifier_1", targetId = "my_component_identifier_2", type = "depends on"
| table sourceId, targetId, type
```