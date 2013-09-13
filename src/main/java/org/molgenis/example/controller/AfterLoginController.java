package org.molgenis.example.controller;

import static org.molgenis.example.controller.AfterLoginController.URI;

import org.molgenis.framework.ui.MolgenisPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(URI)
public class AfterLoginController extends MolgenisPlugin
{
	public static final String URI = MolgenisPlugin.PLUGIN_URI_PREFIX + "afterlogin";

	public AfterLoginController()
	{
		super(URI);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model)
	{
		model.addAttribute("message", "This is an extra view because you logged in!");
		return "view-afterlogin";
	}
}
