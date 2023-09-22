# The worst movie

This API obtains the producer with the longest interval between two consecutive awards, and the one that obtained two awards the fastest, based on a CSV file that is imported into the database when starting the program.


## API Reference

#### Search for producers who won the award in the smallest and largest range

```http
  GET /producers/winners
```

It renurs an object like this:
```json
  {
    "min": [
        {
            "producer": "producer name",
            "interval": 1,
            "previousWin": 1990,
            "followingWin": 1991
        }
    ],
    "max": [
        {
            "producer": "another producer name",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ]
  }
```

## Running Tests

To run tests, run the following command

```bash
   ./mvnw clean test
```

