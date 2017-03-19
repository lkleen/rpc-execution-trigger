yql test query: https://developer.yahoo.com/yql/

example: select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2015-01-01" and endDate = "2016-01-01"

env 'store://datatables.org/alltableswithkeys'; select * from yahoo.finance.quotes where symbol in ("YHOO","AAPL","GOOG","MSFT")
env 'store://datatables.org/alltableswithkeys'; select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2015-01-01" and endDate = "2016-01-01"

# limitations
see: https://developer.yahoo.com/yql/guide/usage_info_limits.html
- max. 2000 queries/hour if no api key