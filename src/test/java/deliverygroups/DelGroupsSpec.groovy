package deliverygroups

import spock.lang.Specification

import javax.json.Json
import javax.json.JsonObject

class DelGroupsSpec extends Specification {

    def allGroups = ['CC', 'CP', 'UK', 'INT'] as Set

    def 'One item all available'(){

        given:
        def basket = oneItemAllAvailable

        when:
        def groups = getGroupsForBasket(basket)

        then:
        groups == ['CP', 'CC', 'UK', 'INT'] as Set

    }

    def 'One item, 2 available'(){

        given:
        def basket = oneItemCollectNotAvailable

        when:
        def groups = getGroupsForBasket(basket)

        then:
        groups == ['UK', 'INT'] as Set

    }

    def 'Two items, only 1 shared available'() {

        given:
        def basket = twoItemsOneSharedAvailable

        when:
        def groups = getGroupsForBasket(basket)

        then:
        groups == ['UK'] as Set

    }

    def 'One item all available: methods'(){

        given:
        def basket = oneItemAllAvailable

        when:
        def methods = getMethodsForBasket(basket)

        then:
        methods == [1,2,3,4,5,6,7,8]

    }

    def 'One item collect not available: methods'(){

        given:
        def basket = oneItemCollectNotAvailable

        when:
        def methods = getMethodsForBasket(basket)

        then:
        methods == [3,4,5,6,7,8]

    }

    def getMethodsForBasket(JsonObject jsonObject) {
        def groups = getGroupsForBasket(jsonObject)
        def response = []

        if (groups.contains('CC')){
            response.add(1)
        }
        if (groups.contains('CP')){
            response.add(2)
        }
        if (groups.contains('UK')){
            response.addAll([3,4,5])
        }
        if (groups.contains('INT')){
            response.addAll([6,7,8])
        }

        return response
    }

    def getGroupsForBasket(JsonObject jsonObject) {

        def items = jsonObject.items

        def available = [] as Set
        available.addAll(allGroups)

        for (JsonObject item : items) {
            println(item.id)
            def thisItemsSet = [] as Set
            for (JsonObject option: item.deliveryOptions) {
                def type = option.getString('title')
                thisItemsSet.add(type)
            }
            available.retainAll(thisItemsSet)
        }

        println(available)

        return available;
    }

    def getOneItemAllAvailable() {
        JsonObjectMaker.from("""{
            "orderId": "o1234",
            "items": [
              {
                "id": "ci9000001",
                "deliveryOptions": [
                  {
                    "title": "UK",
                    "isAvailable": true,
                    "name": "Delivery to UK"
                  },
                  {
                    "title": "CC",
                    "isAvailable": true,
                    "name": "Click & collect via John Lewis and Waitrose"
                  },
                  {
                    "title": "CP",
                    "isAvailable": false,
                    "name": "Collect via Collect<b>+</b><br>not available"
                  },
                  {
                    "title": "INT",
                    "isAvailable": false,
                    "name": "International delivery not available"
                  }
                ]
              }
            ]
        }""")
    }

    def getOneItemCollectNotAvailable() {
        JsonObjectMaker.from("""{
            "orderId": "o1234",
            "items": [
              {
                "id": "ci9000001",
                "deliveryOptions": [
                  {
                    "title": "UK",
                    "isAvailable": true,
                    "name": "Delivery to UK"
                  },
                  {
                    "title": "INT",
                    "isAvailable": false,
                    "name": "International delivery not available"
                  }
                ]
              }
            ]
        }""")
    }

    def getTwoItemsOneSharedAvailable() {
        JsonObjectMaker.from("""{
            "orderId": "o1234",
            "items": [
              {
                "id": "ci9000001",
                "deliveryOptions": [
                  {
                    "title": "UK",
                    "isAvailable": true,
                    "name": "Delivery to UK"
                  },
                  {
                    "title": "CC",
                    "isAvailable": false,
                    "name": "International delivery not available"
                  }
                ]
              },
              {
                "id": "ci9000002",
                "deliveryOptions": [
                  {
                    "title": "UK",
                    "isAvailable": true,
                    "name": "Delivery to UK"
                  },
                  {
                    "title": "INT",
                    "isAvailable": false,
                    "name": "International delivery not available"
                  }
                ]
              }
            ]
        }""")
    }

    def getNoDelMethods() {
        JsonObjectMaker.from("""{
          "deliveryMethods": []
          }""")
    }

    def getAllDelMethods() {
        JsonObjectMaker.from("""{
          "deliveryMethods": [
            {
              "id": "1",
              "group": "Collection",
              "description": "Click & Collect from John Lewis or Waitrose",
              "price": 0.00
            },
            {
              "id": "2",
              "group": "Collection",
              "description": "Collect+ from a local shop from Friday",
              "price": 3.50
            },
            {
              "id": "3",
              "group": "UK",
              "description": "Standard delivery in 5 working days",
              "price": 0.00
            },
            {
              "id": "4",
              "group": "UK",
              "description": "Next or named day delivery",
              "price": 6.95
            },
            {
              "id": "5",
              "group": "UK",
              "description": "Next or named morning delivery",
              "price": 9.95
            },
            {
              "id": "6",
              "group": "International",
              "description": "Europe (EU)",
              "price": 7.50
            },
            {
              "id": "7",
              "group": "International",
              "description": "Europe (Non EU)",
              "price": 7.95
            },
            {
              "id": "8",
              "group": "International",
              "description": "Rest of World",
              "price": 10.00
            }

          ]

        }""")
    }

    def getAllDelMethodsExceptionCollect() {
        JsonObjectMaker.from("""{
          "deliveryMethods": [
            {
              "id": "3",
              "group": "UK",
              "description": "Standard delivery in 5 working days",
              "price": 0.00
            },
            {
              "id": "4",
              "group": "UK",
              "description": "Next or named day delivery",
              "price": 6.95
            },
            {
              "id": "5",
              "group": "UK",
              "description": "Next or named morning delivery",
              "price": 9.95
            },
            {
              "id": "6",
              "group": "International",
              "description": "Europe (EU)",
              "price": 7.50
            },
            {
              "id": "7",
              "group": "International",
              "description": "Europe (Non EU)",
              "price": 7.95
            },
            {
              "id": "8",
              "group": "International",
              "description": "Rest of World",
              "price": 10.00
            }

          ]

        }""")
    }

    def getUKDelMethodsOnly() {
        JsonObjectMaker.from("""{
          "deliveryMethods": [

            {
              "id": "3",
              "group": "UK",
              "description": "Standard delivery in 5 working days",
              "price": 0.00
            },
            {
              "id": "4",
              "group": "UK",
              "description": "Next or named day delivery",
              "price": 6.95
            },
            {
              "id": "5",
              "group": "UK",
              "description": "Next or named morning delivery",
              "price": 9.95
            }

          ]

        }""")
    }

    class JsonObjectMaker {
        static JsonObject from(String jsonString) {
            return Json.createReader(new StringReader(jsonString)).readObject();
        }
    }


}
