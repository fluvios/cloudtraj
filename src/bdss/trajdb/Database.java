package bdss.trajdb;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.Operators;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

import bdss.model.Trip;

public class Database {	
	String connectionString = "DefaultEndpointsProtocol=https;AccountName=master-bdss;"
			+ "AccountKey=4geOgLkJwa1HQrAptqgopsnKnFTRCKrUnVpEcuAE7WIJx9jzzZcNnUZPp76KZPzjxjBYdaxUx6ctAjSmMQgxVA==;"
			+ "TableEndpoint=https://master-bdss.table.cosmos.azure.com:443/;";
	CloudStorageAccount storageAccount;
	CloudTableClient tableClient;
	CloudTable cloudTable;
	
	public void connect(){
		try
		{
		    // Retrieve storage account from connection-string.
			storageAccount = CloudStorageAccount.parse(connectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create the table if it doesn't exist.
		    String tableName = "trajectory";
		    cloudTable = tableClient.getTableReference(tableName);
		    cloudTable.createIfNotExists();
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
	
	public void insert(List<Trip> trips) {
		try
		{
		    // Add trajectory entity to the table.
		    for (Trip t : trips) {
		        // Create an operation to add the new taxi to the trajectory table.
		        TableOperation addTrip = TableOperation.insertOrReplace(t);

		        // Execute the batch of operations on the "people" table.
		        cloudTable.execute(addTrip);

			}
		    
		    // Return Message
		    System.out.println("Insert Success!!!");
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
	
	public void pathQuery(String taxiId,String startDate, String endDate) {
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";

		    // Create a filter condition where the partition key is "Smith".
		    String partitionFilter = TableQuery.generateFilterCondition(
		    		ROW_KEY,
		        QueryComparisons.EQUAL,
		        taxiId);

		    // Create a filter condition where the row key is less than the letter "E".
		    String startFilter = TableQuery.generateFilterCondition(
		        PARTITION_KEY,
		        QueryComparisons.GREATER_THAN_OR_EQUAL,
		        startDate);
		    
		    String endFilter = TableQuery.generateFilterCondition(
			        PARTITION_KEY,
			        QueryComparisons.LESS_THAN,
			        startDate);

		    // Combine the two conditions into a filter expression.
		    String combinedFilter = TableQuery.combineFilters(startFilter,
		        Operators.AND, endFilter);
		    combinedFilter = TableQuery.combineFilters(combinedFilter, 
		    		Operators.AND, partitionFilter);

		    // Specify a range query, using "Smith" as the partition key,
		    // with the row key being up to the letter "E".
		    TableQuery<Trip> rangeQuery =
		        TableQuery.from(Trip.class)
		        .where(combinedFilter);

		    // Loop through the results, displaying information about the entity
		    for (Trip entity : cloudTable.execute(rangeQuery)) {
		        System.out.println(entity.getPartitionKey() +
		            " " + entity.getRowKey());
		    }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
	
	public void stQuery(String regionId,String startDate,String endDate) {
		
	}
	
	public void taxiIdList(String taxiId) {
    	List<String> tempTaxiId = new ArrayList<String>();
    			
	    // Define constants for filters.
	    final String ROW_KEY = "RowKey";

	    // Create a filter condition where the partition key is "Smith".
	    String partitionFilter = TableQuery.generateFilterCondition(
	    		ROW_KEY,
	        QueryComparisons.EQUAL,
	        taxiId);
	    
    	// with the row key being up to the letter "E".
	    TableQuery<Trip> rangeQuery =
	        TableQuery.from(Trip.class).where(partitionFilter);

	    // Loop through the results, displaying information about the entity
	    int i = 1;
	    for (Trip entity : cloudTable.execute(rangeQuery)) {
	    	System.out.println("Trip "+ i);
//	    	String[] latlon = entity.getPOLYLINE().split(",");
//	    	for (String temp : latlon) {
//				System.out.println(temp);
//			}
	    	i++;
	    }
	}
}
