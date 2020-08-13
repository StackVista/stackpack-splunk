element = topologyElement.asReadonlyMap()

externalId = element["externalId"]
type = element["typeName"].toLowerCase()
data = element["data"]

identifiers = new HashSet()

// split labels payload based on comma
if (element.data.containsKey('identifiers') && element.data.identifiers instanceof String) {
    splittedIdentifiers = element.data.identifiers.split(',').toList()
    element.data.put("identifiers", splittedIdentifiers)
}

if(data.containsKey("identifiers") && data["identifiers"] instanceof List<String>) {
    data["identifiers"].each{ id ->
        identifiers.add(id)
    }
}

return Sts.createId(externalId, identifiers, type)
