import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Map;

public class Profiler {
    public static void main(String[] args) {
        // Profiling Lua Script Execution
        profileLuaScript();

        // Profiling YAML Parsing
        profileYAMLParsing();
    }

    private static void profileLuaScript() {
        // Load the Lua script
        String luaScript = "print('Hello, Lua!')"; // Replace with your Lua script

        // Create a new Lua environment
        Globals globals = JsePlatform.standardGlobals();

        // Start CPU time measurement
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long startCpuTime = threadMXBean.getCurrentThreadCpuTime();

        // Execute the Lua script
        LuaValue chunk = globals.load(luaScript);
        chunk.call();

        // End CPU time measurement
        long endCpuTime = threadMXBean.getCurrentThreadCpuTime();
        long cpuTime = (endCpuTime - startCpuTime) / 1000000; // Convert to milliseconds

        // Print CPU time
        System.out.println("Lua Script CPU Time: " + cpuTime + " ms");

        // Print memory usage
        Runtime runtime = Runtime.getRuntime();
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Lua Script Memory Used: " + memoryUsed + " bytes");
    }

    private static void profileYAMLParsing() {
        // Load the YAML file
        String yamlFile = "# Recursive Cognitive Structure in YAML.yml"; // Replace with your YAML file path

        // Start CPU time measurement
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long startCpuTime = threadMXBean.getCurrentThreadCpuTime();

        // Parse the YAML file
        Yaml yaml = new Yaml();
        try (FileInputStream fis = new FileInputStream(yamlFile)) {
            Map<String, Object> data = yaml.load(fis);
            // You can process the data here if needed
        } catch (IOException e) {
            e.printStackTrace();
        }

        // End CPU time measurement
        long endCpuTime = threadMXBean.getCurrentThreadCpuTime();
        long cpuTime = (endCpuTime - startCpuTime) / 1000000; // Convert to milliseconds

        // Print CPU time
        System.out.println("YAML Parsing CPU Time: " + cpuTime + " ms");

        // Print memory usage
        Runtime runtime = Runtime.getRuntime();
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("YAML Parsing Memory Used: " + memoryUsed + " bytes");
    }
}