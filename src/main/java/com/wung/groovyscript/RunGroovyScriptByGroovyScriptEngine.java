/*
 * Copyright (C), 2011-2018.
 */
package com.wung.groovyscript;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wung 2018/11/7.
 */
public class RunGroovyScriptByGroovyScriptEngine {

    public Object executeByFile(String[] filepath, String filename, Map<String, Object> params) throws Exception {
        if (filepath == null) {
            filepath = new String[] { "src/main/java/groovyscript" };
        }

        GroovyScriptEngine engine = new GroovyScriptEngine(filepath);
        return engine.run(filename, new Binding(params));
    }

    public Object executeByFile(String[] filepath, String filename, String methodName, Object[] params) throws Exception {
        if (filepath == null) {
            filepath = new String[] { "src/main/java/com/wung/groovyscript/" };
        }

        GroovyScriptEngine engine = new GroovyScriptEngine(filepath);
        Script script = engine.createScript(filename, new Binding());
        return script.invokeMethod(methodName, params);
    }

    public Object executeByScriptString(String script, Map<String, Object> params) throws Exception {
        if (script == null || "".equals(script)) {
            throw new RuntimeException("传入的脚本为空");
        }

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        Bindings bindings = engine.createBindings();
        bindings.putAll(params);
        return engine.eval(script, bindings);
    }
	
	public Object executeByScriptString(String script, String methodName, Object[] params) {
		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("groovy");
			engine.eval(script);
			Invocable inv = (Invocable) engine;
			return inv.invokeFunction(methodName, params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		RunGroovyScriptByGroovyScriptEngine groovy = new RunGroovyScriptByGroovyScriptEngine();
		Map<String, Object> params = new HashMap<>();
		params.put("language", "groovy test");
		
		String scriptString = "return \"Hello $language\"";
		Object res = groovy.executeByScriptString(scriptString, params);
		System.out.println(res);
		
		scriptString = "def hello(param1, param2) {return \"the params is $param1 and $param2\"}";
		Object res1 = groovy.executeByScriptString(scriptString, "hello", new String[] { "jack", "json" });
		System.out.println(res1);
		
		Object res3 = groovy.executeByFile(null, "hello.groovy", "hello", new String[]{"jack", "json"});
		System.out.println(res3);

	}

}
