package sample;

public class ConfigData {
    public String csConfig = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<configuration>\n" +
            "    <startup> \n" +
            "        <supportedRuntime version=\"v4.0\" sku=\".NETFramework,Version=v4.5\" />\n" +
            "    </startup>\n" +
            "  <runtime>\n" +
            "    <assemblyBinding xmlns=\"urn:schemas-microsoft-com:asm.v1\">\n" +
            "      <dependentAssembly>\n" +
            "        <assemblyIdentity name=\"log4net\" publicKeyToken=\"669e0ddf0bb1aa2a\" culture=\"neutral\" />\n" +
            "        <bindingRedirect oldVersion=\"0.0.0.0-1.2.13.0\" newVersion=\"1.2.13.0\" />\n" +
            "      </dependentAssembly>\n" +
            "      <dependentAssembly>\n" +
            "        <assemblyIdentity name=\"Newtonsoft.Json\" publicKeyToken=\"30ad4fe6b2a6aeed\" culture=\"neutral\" />\n" +
            "        <bindingRedirect oldVersion=\"0.0.0.0-6.0.0.0\" newVersion=\"6.0.0.0\" />\n" +
            "      </dependentAssembly>\n" +
            "    </assemblyBinding>\n" +
            "  </runtime>\n" +
            "  <appSettings>\n" +
            "\t\t<add key = \"url\" value = \"wss://console.mysocialseller.com//console/1/1/\"/>\n" +
            "\t\t<add key = \"output\" value = \"głśnik\"/>\n" +
            "\t</appSettings>\n" +
            "</configuration>";
    public String jConfig = "server.port=2300\n" +
            "id = 1\n" +
            "scen.id = 12751\n" +
            "scen.server=console.mysocialseller.com\n" +
            "scen.microphoneportindex = 1\n" +
            "scen.grammarPath='C:\\\\sphinx-listener\\\\src\\\\resources\\\\grammars'\n" +
            "scen.grammarName=grammar";
}
