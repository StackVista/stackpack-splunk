import groovy.json.JsonSlurper
import com.stackstate.stackpack.ProvisioningScript
import com.stackstate.stackpack.ProvisioningContext
import com.stackstate.stackpack.ProvisioningIO
import com.stackstate.stackpack.Version

class SplunkTopologyProvision extends ProvisioningScript {
  SplunkTopologyProvision(ProvisioningContext context) {
    super(context)
  }

  @Override
  ProvisioningIO<scala.Unit> install(Map<String, Object> config) {
    def instance_name = config.splunk_instance_name.trim()
    def templateArguments = [
            'topicName'   : topicName(config),
            'instanceName': instance_name,
            'instanceId'  : context().instance().id()
    ]
    templateArguments.putAll(config)

    return context().stackPack().importSnapshot("templates/splunk-topology-template.stj", [:]) >>
            context().instance().importSnapshot("templates/splunk-topology-instance-template.stj", templateArguments)
  }

  @Override
  ProvisioningIO<scala.Unit> upgrade(Map<String, Object> config, Version current) {
    return install(config)
  }

  @Override
  void waitingForData(Map<String, Object> config) {
    context().sts().onDataReceived(topicName(config), {
      context().sts().provisioningComplete()
    })
  }

  private def topicName(Map<String, Object> config) {
    def url = config.sts_instance_url.trim()
    def type = 'splunk'
    return context().sts().createTopologyTopicName(type, url)
  }
}
