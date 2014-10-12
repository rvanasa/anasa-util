package net.anasa.math.module.provided.ui;

import net.anasa.util.Debug;
import net.anasa.util.Mapping;
import net.anasa.util.data.properties.Properties;
import net.anasa.util.data.properties.PropertiesException;

public class AspectData
{
	private final Mapping<String, String> data = new Mapping<>();
	
	public AspectData(Properties props, ComponentBuilder<?> builder)
	{
		for(ComponentBuilder<?>.ComponentAspect<?> aspect : builder.getAspects())
		{
			if(props.hasKey(aspect.getName()))
			{
				try
				{
					data.put(aspect.getName(), props.getString(aspect.getName()));
				}
				catch(PropertiesException e)
				{
					Debug.log("Failed to register value for aspect: " + aspect.getName());
					e.printStackTrace();
				}
			}
		}
	}
	
	public String data(ComponentBuilder<?>.ComponentAspect<?> aspect)
	{
		return data.get(aspect.getName());
	}
	
	public <T> T getValue(ComponentBuilder<?>.ComponentAspect<T> aspect)
	{
		return aspect.getValue(this);
	}
}