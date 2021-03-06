// Set some sane defaults.

def labels = new ArrayList()

if(!element.data.containsKey("environment")) {
    element.data.put("environment", "Production")
}

if(!element.data.containsKey("domain")) {
    element.data.put("domain", "Splunk")
}

if(!element.data.containsKey("layer")) {
    element.data.put("layer", "Uncategorized")
}

// split labels payload based on comma

if (element.data.containsKey('labels') && element.data.labels instanceof String) {
    labels = element.data.labels.split(',').toList()
}

labels << "splunk-instance:{{instanceName}}"

element.data.put("labels", labels)

element
