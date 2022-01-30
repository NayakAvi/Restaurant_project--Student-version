import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        String restName ="Amelie's cafe";
        service.removeRestaurant(restName);
        final Restaurant restaurantResponse = service.findRestaurantByName(restName);
        assertEquals(restName, restaurantResponse.getName());
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("xyz"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        addRestauraent("Amelie's cafe","Chennai");


        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        addRestauraent("Amelie's cafe","Chennai");

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        addRestauraent("Amelie's cafe","Chennai");

        int initialNumberOfRestaurants = service.getRestaurants().size();
        addRestauraent("Pumpkin Tales","Chennai");
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

    private void addRestauraent(final String restName,String location){
        final Restaurant restaurant = service.addRestaurant(restName,location,LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));

        if(restName.equals("Amelie's cafe")){
            restaurant.addToMenu("Sweet corn soup",119);
            restaurant.addToMenu("Vegetable lasagne", 269);
        }


    }


    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}