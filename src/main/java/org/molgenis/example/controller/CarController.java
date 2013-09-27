package org.molgenis.example.controller;

import static org.molgenis.example.controller.CarController.URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.molgenis.framework.db.Database;
import org.molgenis.framework.ui.MolgenisPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(URI)
public class CarController extends MolgenisPlugin
{
	public static final String URI = MolgenisPlugin.PLUGIN_URI_PREFIX + "car";

	private final List<Car> cars;

	@Autowired
	public CarController(Database database)
	{
		super(URI);
		cars = new ArrayList<Car>();
		cars.add(new Car(0, "Audi", Arrays.asList("A3", "A4", "A5", "A6", "A7", "A8", "Q7")));
		cars.add(new Car(1, "BMW", Arrays.asList("3 series", "5 series", "6 series", "7 series", "X3", "X5", "Z4")));
		cars.add(new Car(2, "Citroen", Arrays.asList("C1", "C3", "C4", "C5", "C6", "C8", "Picasso")));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model)
	{
		model.addAttribute("cars", cars);
		return "view-car";
	}

	@RequestMapping(value = "/view/allcars", method = RequestMethod.GET)
	public String viewCars(Model model)
	{
		model.addAttribute("cars", cars);
		return "view-all-cars";
	}

	@RequestMapping(value = "/view/allcars/add", method = RequestMethod.POST)
	@ResponseBody
	public Car addCar(@RequestBody
	Car car, Model model)
	{
		cars.add(car);
		model.addAttribute("cars", cars);
		return car;
	}

	@RequestMapping(value = "/brand", method = RequestMethod.GET)
	public String select(@RequestParam
	Integer carId, Model model)
	{
		for (Car car : cars)
		{
			if (car.getId().equals(carId))
			{
				model.addAttribute("types", car.getListOfTypes());
				model.addAttribute("selectedCar", car);
			}
		}
		return "view-car-brand";
	}

	@RequestMapping(value = "/brand/type", method = RequestMethod.GET)
	public String carType(@RequestParam
	String selectedType, Model model)
	{
		model.addAttribute("type", selectedType);
		return "view-car-type";
	}

	public static class Car
	{
		private final Integer id;
		private final String name;
		private final List<String> listOfTypes;

		public Car(Integer id, String name, List<String> listOfTypes)
		{
			this.id = id;
			this.name = name;
			this.listOfTypes = listOfTypes;
		}

		public Integer getId()
		{
			return id;
		}

		public String getName()
		{
			return name;
		}

		public List<String> getListOfTypes()
		{
			return listOfTypes;
		}
	}
}