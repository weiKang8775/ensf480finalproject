package view;

import model.shared.Address;
import model.user.Card;

public interface ViewRegisterListener {
	void register(String email, String name, String password, Address address, Card card);
}
