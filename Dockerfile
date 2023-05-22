ARG IMAGE=intersystemsdc/iris-community:2020.4.0.547.0-zpm
ARG IMAGE=intersystemsdc/iris-community
FROM $IMAGE

USER root   
        
WORKDIR /opt/irisbuild
RUN chown ${ISC_PACKAGE_MGRUSER}:${ISC_PACKAGE_IRISGROUP} /opt/irisbuild
USER ${ISC_PACKAGE_MGRUSER}

COPY iris.script iris.script
COPY PdfGenerator.cls src/PdfGenerator.cls

RUN iris start IRIS \
	&& iris session IRIS < iris.script \
    && iris stop IRIS quietly
