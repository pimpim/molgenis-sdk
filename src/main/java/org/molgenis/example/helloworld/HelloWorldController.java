package org.molgenis.example.helloworld;

import static org.molgenis.example.helloworld.HelloWorldController.URI;

import org.molgenis.framework.ui.MolgenisPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(URI)
public class HelloWorldController extends MolgenisPlugin
{
	public static final String URI = MolgenisPlugin.PLUGIN_URI_PREFIX + "helloworld";

	public HelloWorldController()
	{
		super(URI);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model)
	{
		model.addAttribute("message", "Hello world!");
		return "view-helloworld";
	}
}
