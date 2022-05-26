package com.jacaranda.country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Country {
	private int countryId;
	private String countryName;
	private ArrayList<City> listCity;

	public Country(int countryId, String countryName) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.listCity = new ArrayList<>();
	}

	public City searchIdCity(City city2) {
		City city = null;
		int position = this.listCity.indexOf(city2);

		if (position != -1) {
			city= this.listCity.get(position);
		}

		return city;
	}

	public void addCity(City city) {
		this.listCity.add(city);
	}
	
	public void addAddress(City aux, Address nueva) {
		int position = this.listCity.indexOf(aux);
		if(position != -1) {
			this.listCity.get(position).addAddress(nueva);
		}
	}

	public int getCountryId() {
		return countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public String writeFile() {
		int numAddress = 0;
		Collections.sort(this.listCity);

		for (City aux : this.listCity) {
			numAddress += aux.getAddressNum();
		}
		
		return "Id:" + this.countryId + ", country name:" + this.countryName + ", number city:" + this.listCity.size()
				+ ", number addres:" + numAddress;
	}
	
	public String writeCityFile() {
		StringBuilder text = new StringBuilder();
		
		for(City aux : this.listCity) {
			text.append(aux.writeAddressFile() + "\n");
		}
		
		return "Country name:" + this.countryName + "\n" + text.toString();
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", listCity=" + listCity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return countryId == other.countryId;
	}

}
