package org.molgenis.example;

import org.molgenis.framework.server.MolgenisSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SdkConfig
{
	@Bean
	public MolgenisSettings molgenisSettings()
	{
		return new MolgenisDbSettings();
	}
}
