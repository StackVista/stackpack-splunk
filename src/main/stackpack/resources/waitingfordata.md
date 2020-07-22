## The Splunk Topology StackPack is waiting for your action

Provisioning of StackState configuration is done. We are now ready to process your data. If you haven't installed the StackState API Integration Agent please do so. After the installation of the API Integration Agent  we can configure the Splunk Topology integration.
 
### Installation

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
