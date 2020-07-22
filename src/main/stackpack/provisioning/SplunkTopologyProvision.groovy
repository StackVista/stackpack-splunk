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
    def cleanConfig = cleanupConfig(config)

    def templateArguments = [
      'topicName': topicName(cleanConfig),
      'integrationType': cleanConfig.sts_instance_type,
      'integrationUrl': integrationUrl(cleanConfig),
      'instanceId': context().instance().id()
    ]
    templateArguments.putAll(cleanConfig)

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

  private def cleanupConfig(Map<String, Object> config) {
    def cleanConfig = [:]
    cleanConfig.putAll(config)
    cleanConfig.sts_instance_url = config.sts_instance_url.trim()
    cleanConfig.sts_instance_type = 'splunk'

    return cleanConfig
  }

  private def topicName(Map<String, Object> config) {
    def url = config.sts_instance_url.trim()
    def type = 'splunk'
    return context().sts().createTopologyTopicName(type, url)
  }

  private def integrationUrl(Map<String, Object> config) {
    return config.sts_instance_url.trim()
  }
}