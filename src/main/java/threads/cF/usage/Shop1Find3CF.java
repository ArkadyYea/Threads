package threads.cF.usage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Shop1Find3CF {
	
	List<Shop1> shops = Arrays.asList(new Shop1("Shop1"), new Shop1("Shop2"), new Shop1("Shop3"), new Shop1("Shop4"));
	//List<Shop> shops = Arrays.asList(new Shop("Shop1"), new Shop("Shop2"), new Shop("Shop3"), new Shop("Shop4"), new Shop("Shop5"), new Shop("Shop6"), new Shop("Shop7"), new Shop("Shop8"));
	
	public Shop1Find3CF() throws InterruptedException, ExecutionException {
		
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("\nDone in " + duration + " msecs");
		
	}
	
	public List<String> findPrices(String product) {
		List<CompletableFuture<String>> priceFutures =
			shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
				() -> String.format("%s price is %.2f, %s",
					shop.getName(), shop.getPrice("Zupa"), Thread.currentThread().getName()) ))
			.collect(Collectors.toList());
		
		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws Exception {
		new Shop1Find3CF();
	}
	
}
