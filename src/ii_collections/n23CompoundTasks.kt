package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return customers.filter {
        it.orders.any { it.products.contains(product) }
    }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return orders
            .filter { it.isDelivered }
            .flatMap { it.products }
            .maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
//    var result = 1
//    customers.forEach {
//        it.orders.forEach {
//            result += it.products.count { it == product }
//        }
//    }
//    return result


    return customers.fold(0, { pre, customer ->
        customer.orders.fold(
                pre, { pre2, order -> pre2 + order.products.count { it == product }
        })

    })

}
