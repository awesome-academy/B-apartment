package b.apartment.util;

import java.util.Collections;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import b.apartment.configuration.ApplicationContextProvider;
import b.apartment.uploader.ImageUploader;

public class ViewSupportDialect extends AbstractDialect implements IExpressionObjectDialect {

	private ImageUploader imageUploader;

	public ViewSupportDialect() {
		super("PrettyTime Dialect");
		this.imageUploader = ApplicationContextProvider.getApplicationContext().getBean("imageUploader",
				ImageUploader.class);
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {

			@Override
			public Set<String> getAllExpressionObjectNames() {
				return Collections.singleton("viewSupport");
			}

			@Override
			public Object buildObject(IExpressionContext context, String expressionObjectName) {
				return new ViewSupportUtil(imageUploader);
			}

			@Override
			public boolean isCacheable(String expressionObjectName) {
				return true;
			}
		};
	}
}