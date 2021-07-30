## python artifactory dependency
mkdir -p ~/.pip/ && touch ~/.pip/pip.conf
echo "[global]" > ~/.pip/pip.conf
echo "extra-index-url = https://$ARTIFACTORY_USER:$ARTIFACTORY_PASSWORD@artifactory.stackstate.io/artifactory/api/pypi/pypi-local/simple" >> ~/.pip/pip.conf