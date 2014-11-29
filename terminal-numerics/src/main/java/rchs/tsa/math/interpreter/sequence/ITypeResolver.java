package rchs.tsa.math.interpreter.sequence;

import rchs.tsa.math.sequence.TokenType;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.resolver.IToken;
import net.anasa.util.data.resolver.ResolverException;
import net.anasa.util.data.resolver.logic.IResolver;

public interface ITypeResolver<K> extends IResolver<K>
{
	public TokenType getType();
	
	@Override
	default boolean matches(Listing<IToken> data)
	{
		if(data.size() != 1)
		{
			return false;
		}
		
		IToken item = data.get(0);
		return StringHelper.equals(getType().name(), item.getType()) && matches(item);
	}
	
	default boolean matches(IToken item)
	{
		return true;
	}
	
	@Override
	default K resolve(Listing<IToken> data) throws ResolverException
	{
		return resolve(data.get(0));
	}
	
	public K resolve(IToken item) throws ResolverException;
}