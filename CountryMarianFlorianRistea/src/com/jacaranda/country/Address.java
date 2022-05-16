package com.jacaranda.country;

import java.util.Objects;

public class Address {
	private int addressId;
	private String addressName;
	
	public Address(int addressId, String addressName) {
		super();
		this.addressId = addressId;
		this.addressName = addressName;
	}

	public int getAddressId() {
		return addressId;
	}

	public String getAddress() {
		return addressName;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", address=" + addressName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressId == other.addressId;
	}
	
}
