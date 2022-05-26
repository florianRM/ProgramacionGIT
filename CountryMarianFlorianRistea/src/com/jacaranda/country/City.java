package com.jacaranda.country;

import java.util.ArrayList;
import java.util.Objects;

public class City implements Comparable<City> {
	private int cityId;
	private String cityName;
	private ArrayList<Address> listAddress;
	
	public City(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.listAddress = new ArrayList<>();
	}
	
	public void addAddress(Address calle) {
		this.listAddress.add(calle);
	}
	
	public int getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}
	
	public int getAddressNum() {
		return this.listAddress.size();
	}
	
	public String writeAddressFile() {
		StringBuilder text = new StringBuilder();
		
		for(Address aux : this.listAddress) {
			if(text.length() != 0) {
				text.append(", " + aux.getAddress());
			} else {
				text.append(aux.getAddress());
			}
		}
		return "City name:" + this.cityName + ", address:" + text.toString();
	}

	@Override
	public String toString() {
		return "City [city_id=" + cityId + ", cityName=" + cityName + ", listAddress=" + listAddress + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return cityId == other.cityId;
	}

	@Override
	public int compareTo(City o) {
		int resultado = this.listAddress.size() - o.listAddress.size();
		if(resultado == 0) {
			resultado = this.cityName.compareTo(o.cityName);
		}
		return resultado;
	}
}
