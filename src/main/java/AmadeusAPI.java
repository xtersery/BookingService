import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.shopping.FlightOffersSearch;

public class AmadeusAPI {
    Amadeus amadeus = Amadeus.builder("jfTNog6caakx8GTGzhniCBLLAtJjvwhW",
                    "YHInUsAPvh5c4Psl")
            .build();

    public void postRequest() throws ResponseException {
        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
          Params.with("originLocationCode", "LON")
                  .and("destinationLocationCode", "NYC")
                  .and("departureDate", "2023-07-21")
                  .and("returnDate", "2023-08-15")
                  .and("adults", 1)
                  .and("max", 2)
        );

        for (FlightOfferSearch search: flightOffersSearches) {
            System.out.println(search.toString());
        }

    }

    public static void main(String[] args) {
        AmadeusAPI api = new AmadeusAPI();
        try {
            api.postRequest();
        } catch (ResponseException e) {
            System.out.println(e.getMessage());
        }
    }

}
