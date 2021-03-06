import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe","Chennai",LocalTime.parse("09:30:00"),LocalTime.parse("22:00:00")));
        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe","Chennai",LocalTime.parse("09:30:00"),LocalTime.parse("22:00:00")));
        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:30:00"));
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>Price Calculation<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void total_price_of_menu_item_selected_should_be_item_price() {
        List<String> selectedMenu = new ArrayList<>();
        selectedMenu.add("Sweet corn soup");
        int price = restaurant.calculatePrice(selectedMenu);
        assertEquals(price, 119);
    }

    @Test
    public void total_price_of_menu_item_selected_should_be_sum_of_all_item_price() {
        List<String> selectedMenu = new ArrayList<>();
        selectedMenu.add("Sweet corn soup");
        selectedMenu.add("Vegetable lasagne");
        int price = restaurant.calculatePrice(selectedMenu);
        assertEquals(price, 388);
    }


    //>>>>>>>>>>>>>>>>>>>>>>Price Calculation<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
}