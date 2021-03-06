package rchs.tsa.math.resource.module;

import java.io.IOException;
import java.net.URL;

import net.anasa.util.Checks;
import net.anasa.util.Debug;
import net.anasa.util.data.properties.Properties;
import rchs.tsa.math.TerminalNumerics;
import rchs.tsa.math.expression.MathData;
import rchs.tsa.math.io.xml.layout.XmlLayoutLoader;
import rchs.tsa.math.resource.app.IApp;
import rchs.tsa.math.resource.module.context.IComponentEntry;
import rchs.tsa.math.resource.module.context.ModuleContext;
import rchs.tsa.math.resource.module.context.base.ActionRegister.IComponentAction;

public interface IModuleDelegate
{
	default void init() throws Exception
	{
		
	}
	
	default void setupMathData(MathData data, Properties props)
	{
		
	}
	
	default ModuleContext getContext()
	{
		return TerminalNumerics.getContext();
	}
	
	default void addComponent(String id, IComponentEntry entry)
	{
		getContext().addComponentEntry(id, entry);
	}
	
	default void addComponentLayout(String id, URL layoutURL) throws IOException
	{
		Checks.checkNotNull(layoutURL, new IOException("layout file URL must not be null"));
		
		getContext().addComponentEntry(id, (props) -> new XmlLayoutLoader(getContext()).load(layoutURL.openStream()).compile(getContext()));
	}
	
	default void addAction(String id, IComponentAction action)
	{
		getContext().addAction(id, action);
	}
	
	default void addAction(String id, Runnable action)
	{
		getContext().addAction(id, (component, args) -> action.run());
	}
	
	default void addModule(IModule module)
	{
		getContext().addModule(module);
	}
	
	default void addApp(IApp app)
	{
		getContext().addApp(app);
	}
	
	default void print(Object data)
	{
		Debug.log(data);
	}
}
