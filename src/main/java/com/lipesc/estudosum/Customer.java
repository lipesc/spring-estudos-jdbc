package com.lipesc.estudosum;

public record Customer (int id, String first_name, String last_name)  {

		@Override
		public String toString() {
				return String.format("Customer [id: %d, first_name: %s, last_name: %s%n]", id, first_name, last_name);
		}
}
