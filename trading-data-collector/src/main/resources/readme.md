# alphavantage

see: https://www.alphavantage.co/documentation/#daily

API key: 5R7I2U4PNX5RZBYV

## example:
### (max 100 results)
https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=BMW&outputsize=compact&apikey=5R7I2U4PNX5RZBYV

### all for 20 years
https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=BMW&outputsize=full&apikey=5R7I2U4PNX5RZBYV


# Yahoo (discontinued, gone)
yql test query: https://developer.yahoo.com/yql/

example: select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2015-01-01" and endDate = "2016-01-01"

env 'store://datatables.org/alltableswithkeys'; select * from yahoo.finance.quotes where symbol in ("YHOO","AAPL","GOOG","MSFT")
env 'store://datatables.org/alltableswithkeys'; select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2015-01-01" and endDate = "2016-01-01"

## limitations
see: https://developer.yahoo.com/yql/guide/usage_info_limits.html
- max. 2000 queries/hour if no api key