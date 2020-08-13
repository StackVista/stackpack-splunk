## Deprovisioning...


1. Uninstall/disable the StackState API Integration Agent Splunk Topology integration

    ```
    mv /etc/sts-agent/conf.d/static_topology.yaml /etc/sts-agent/conf.d/static_topology.yaml.disabled
    ```
    
1. Restart the StackState Agent to apply changes

    ```
    systemctl restart stackstate-agent
    ```
