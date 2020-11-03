/// Helper class that demonstrates how to call the TextToPdf Java library
Class User.PdfGenerator
{
ClassMethod StartGateway() as %Net.Remote.Gateway
{
    // Get a ObjectGateway instance that defines the JavaGate server
    set st = ##class(%Net.Remote.Service).OpenGateway("iris-pdf-generator",.OG)

    // Test to make sure the server is running. If not, start it
    if ('##class(%Net.Remote.Service).IsGatewayRunning(OG.Server,OG.Port,,.status)) {
        // Instantiate the server on the host machine
        set status = ##class(%Net.Remote.Service).StartGateway(OG.Name)
    }

    // Connect a Gateway instance to server JavaGate on the host machine
    set GW = ##class(%Net.Remote.Gateway).%New()
    set st = GW.%Connect(OG.Server, OG.Port, "USER")

    Quit GW
}
ClassMethod TextToPdf(textFilename As %String, pdfFilename As %String) as %Status
{
    set GW = ##class(User.PdfGenerator).StartGateway()

    // Use GW connection to create a proxy for the Fruit class
    set ttp = ##class(%Net.Remote.Object).%New(GW,"example.pdfgen.TextToPdf")

    do ttp.generatePDFFromText(textFilename, pdfFilename)

    Quit $$$OK
}

}