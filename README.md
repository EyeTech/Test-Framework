# Rest API
REST API testing with Java or Python code is a critical piece of our test infrastructure. For reference, we will use public apis like google maps API to get details for any address, in json format.
 
[Geocode - 1600+Amphitheatre+Parkway,+Mountain+View,+CA](https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA)
To make a call to this http endpoint with Java, you can use any library like the httpclient utilities in java. just search for them.

You can explore ways to test this for latitude and longitude correctness.
e.g. verify if latitude for above address is as expected 

We are not validating of the real latLng is correct or not. Just checking that it matches what we expect for a given address.

(you can have a data file to store expected results for addresses).

The json response contains the lat long details in 

```
"geometry" : {
            "location" : {
               "lat" : 37.422364,
               "lng" : -122.084364
            }
 }
```

[Source Directory](./src/main/java/com/qa/geocode/) 

[Test Directory](./src/test/java/com/qa/geocode/) 

## Address Mapping
How can you maintain a mapping of addresses and their expected Lat-Long?
* Code see [DataLoader.java](./src/main/java/com/qa/mapping/DataLoader.java)
* Data File see [address-mapping.json](./src/test/resources/address-mapping.json)

Unit test your code.
* Unit Tests [DataLoaderTest.java](./src/test/java/com/qa/mapping/DataLoaderTest.java)

# DEDUP
Write a program to find common elements from two ArrayList of Strings, without using a Set or Map.
public ArrayList<String>  getDeDupedList(ArrayList<String> originalList1, ArrayList<String> originalList2 ){}
 
Returns the list of common elements between the two input lists.

How does using an efficient data structure like HashSet make a difference?
Can you quantify the time difference in execution as the dataset size grows, between the method that uses efficient data structure vs brute force one?
Write unit tests to  ensure your algorithm is correct. For many input lists.

* [CommonElements.java](./src/main/java/com/qa/common/CommonElements.java)
* [Test Directory](./src/test/java/com/qa/common)

## Common Elements
Key to this is removing/not processing repeat elements in the same structure. 

Four DeDupe methods

1) ```getDeDupedList``` - Brute force, compares each element of both sets, does not remove repeat elements. Takes ~ 12 s to complete.

2) ```getDeDupedListDistinctAfter``` - Brute force, compares each element of both sets, removes repeat elements post dedupe. Takes ~ 12 s to complete.

3) ```getDeDupedListDistinctBefore``` - Removes repeat elements pre dedupe. Takes ~ 4 ms to complete.

4) ```getDeDupedHashSet``` - Uses HashSet. Takes ~ 22 ms to complete.