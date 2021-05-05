import org.junit.jupiter.api.*;
//import sun.jvm.hotspot.utilities.Assert;

import java.util.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    static RestaurantService service = new RestaurantService();
    static Restaurant restaurant;
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    //REFACTOR ALL THE REPEATED LINES OF CODE

    static LocalTime openingTime = LocalTime.parse("10:30:00");
    static LocalTime closingTime = LocalTime.parse("22:00:00");

    @BeforeEach
    public void setup() {
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        service.addRestaurant("Some cafe","Chennai",openingTime,closingTime);
    }

    @AfterEach
    public void tearDown() {
        try {
            service.removeRestaurant("Amelie's cafe");
        } catch (RestaurantNotFoundException e) {
            e.printStackTrace();
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws RestaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        //Arrange

        //Act
        Restaurant findByRestaurant = service.findRestaurantByName("Amelie's cafe");

        //Assert
        assertEquals(restaurant, findByRestaurant);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws RestaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        //Act
        try {
            Restaurant findByRestaurant = service.findRestaurantByName("Some Dumm cafe");
        } catch(RestaurantNotFoundException ex) {
            assertTrue(ex instanceof RestaurantNotFoundException);
        }
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws RestaurantNotFoundException {

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Some cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws RestaurantNotFoundException {
        assertThrows(RestaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}