#### Prerequisites

The following prerequisites need to be met:

* An API Integration Agent must be installed which can connect to Splunk and StackState.
* A Splunk instance must be running.


#### Enabling Splunk Topology check

To enable the Splunk Topology check which collects the data from Splunk instance:

Configure the API Integration Agent using the following sequence of steps:

1. Enable the Splunk Topology integration

    ```
    mv /etc/sts-agent/conf.d/splunk_topology.yaml.example /etc/sts-agent/conf.d/splunk_topology.yaml
    ```
2. Edit the configuration file

    ```
    vi /etc/sts-agent/conf.d/splunk_topology.yaml
    ```
  
    Specify Splunk's API URL as provided to the StackPack, authentication, the saved searches to be read, and save the file.
    
    
3. Verify that the integration could read the Splunk saved searches correctly 

    ```
    sts-agent check splunk_topology
    ```

    This command returns a list of components and relations that it collected from Splunk. The command does not send any data to StackState.

4. Restart the StackState Agent to apply changes

    ```
    systemctl restart stackstate-agent
    ```

Once the Agent is restarted, wait for the Agent to collect the data and send it to StackState.
