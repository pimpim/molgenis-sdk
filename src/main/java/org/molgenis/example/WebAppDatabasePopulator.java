package org.molgenis.example;

import org.molgenis.MolgenisDatabasePopulator;
import org.molgenis.example.helloworld.HelloWorldController;
import org.molgenis.framework.db.Database;
import org.molgenis.framework.security.Login;
import org.molgenis.framework.server.MolgenisPermissionService;
import org.molgenis.framework.server.MolgenisPermissionService.Permission;
import org.molgenis.omx.auth.MolgenisUser;
import org.molgenis.omx.auth.OmxPermissionService;
import org.molgenis.ui.MolgenisMenuController.VoidPluginController;
import org.springframework.beans.factory.annotation.Value;

public class WebAppDatabasePopulator extends MolgenisDatabasePopulator
{
	@Value("${admin.password:@admin}")
	private String adminPassword;

	@Override
	protected void initializeApplicationDatabase(Database database) throws Exception
	{

		Login login = database.getLogin();
		database.setLogin(null);
		login.login(database, Login.USER_ADMIN_NAME, adminPassword);

		MolgenisUser anonymousUser = MolgenisUser.findByName(database, Login.USER_ANONYMOUS_NAME);
		MolgenisPermissionService permissionService = new OmxPermissionService(database, login);

		permissionService.setPermissionOnPlugin(VoidPluginController.class, anonymousUser.getId(), Permission.READ);
		permissionService.setPermissionOnPlugin(HelloWorldController.class, anonymousUser.getId(), Permission.READ);

		database.setLogin(login); // restore login
	}
}