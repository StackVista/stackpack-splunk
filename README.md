# StackState Splunk StackPack

This repository contains the StackState Splunk StackPack. The code is distributed under the [Apache 2.0](LICENSE.md) license.

## Introduction

The Splunk StackPack connects Splunk to StackState, allowing you to monitor your Splunk instance(s). You can find the
[capabilities of the StackPack](src/main/stackpack/resources/overview.md) and the [configuration instructions](src/main/stackpack/resources/detailed-overview.md) in this repository.

The Splunk StackPack configures StackState to processes data produced by the Splunk integration in the [StackState agent](https://github.com/StackVista/sts-agent). The Splunk integration can be found in the [integrations repository](https://github.com/StackVista/sts-agent-integrations-core/tree/master/splunk_topology).

## Documentation

The StackState [documentation](https://docs.stackstate.com/develop/creating_stackpacks/) describes how to build and install a StackPack.

## Building

The Splunk StackPack is built using the [SBT](https://www.scala-sbt.org/) tool.

In the main directory, compile the Splunk StackPack using:

```
sbt compile
```

## Testing

The Splunk StackPack comes with a small testing library that validates that the Splunk StackPack packaging is correct.

In the main directory, test the Splunk StackPack using:

```
sbt test
```

## Packaging

In the main directory, package the Splunk StackPack using:

```
sbt package
```

## Versioning

The Splunk StackPack release version is configured in the [version.sbt](version.sbt) file. StackPacks use [Semantic Versioning](https://semver.org/).

## Releasing

use `bin/publish.sh x.y.z`, ensure you are following semver.

## Contributing

Contributions are welcome! Please check our [contribution instructions](CONTRIBUTING.md).

## Legal

Contributions to the Splunk StackPack are contributed under the "inbound=outbound" principle. This means
that contributions are licensed under the same license as the [source code](LICENSE.md).
We do not require you to sign a separate CLA (Contributor License Agreement) or any other kind of document.

Thank you for your contributions!
