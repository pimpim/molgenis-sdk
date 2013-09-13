package org.molgenis.example;

import org.molgenis.MolgenisDatabasePopulator;
import org.molgenis.example.controller.CarController;
import org.molgenis.example.controller.HelloWorldController;
import org.molgenis.framework.db.Database;
import org.molgenis.framework.security.Login;
import org.molgenis.framework.server.MolgenisPermissionService;
import org.molgenis.framework.server.MolgenisPermissionService.Permission;
import org.molgenis.omx.auth.MolgenisUser;
import org.molgenis.omx.auth.OmxPermissionService;
import org.molgenis.omx.auth.util.PasswordHasher;
import org.molgenis.ui.MolgenisMenuController.VoidPluginController;

public class WebAppDatabasePopulator extends MolgenisDatabasePopulator
{
	@Override
	protected void initializeApplicationDatabase(Database database) throws Exception
	{

		Login login = database.getLogin();
		database.setLogin(null);
		login.login(database, Login.USER_ADMIN_NAME, "admin");

		MolgenisUser anonymousUser = MolgenisUser.findByName(database, Login.USER_ANONYMOUS_NAME);
		MolgenisPermissionService permissionService = new OmxPermissionService(database, login);

		permissionService.setPermissionOnPlugin(VoidPluginController.class, anonymousUser.getId(), Permission.READ);
		permissionService.setPermissionOnPlugin(HelloWorldController.class, anonymousUser.getId(), Permission.READ);
		permissionService.setPermissionOnPlugin(CarController.class, anonymousUser.getId(), Permission.READ);

		MolgenisUser adminUser = MolgenisUser.findByName(database, Login.USER_ADMIN_NAME);
		adminUser.setPassword(new PasswordHasher().toMD5("admin"));
		try
		{
			database.beginTx();
			database.update(adminUser);
			database.commitTx();
		}
		catch (Throwable t)
		{
			database.rollbackTx();
		}

		database.setLogin(login); // restore login
	}
}