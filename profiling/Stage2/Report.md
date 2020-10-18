Для нагрузочного тестирования сервера, а также измерения параметров была использована программа wrk, а также профайлер под названием async-profiler. Запросы были сделаны на локальный хост по адресу http://127.0.0.1:8080.

Выполним запрос get с 16 соединениями и 4 потоками, используя следующую команду: wrk -t4 -c16 -d1m -s wrk/get.lua -R3000 --latency http://127.0.0.1:8080. Для формирования запроса мы используем скрипт на языке Lua.


#[Get-запросы]

wrk -t4 -c16 -d1m -s wrk/get.lua -R3000 --latency http://127.0.0.1:8080

Running 1m test @ http://127.0.0.1:8080
  4 threads and 16 connections
  
  Thread Stats   Avg      Stdev     Max   +/- Stdev
  
    Latency     4.68s     4.63s   19.78s    69.44%
    
    Req/Sec   662.48    210.43     1.41k    75.36%
    
  Latency Distribution (HdrHistogram - Recorded Latency)
  
 50.000%    3.73s
 
 75.000%    7.18s 
 
 90.000%   11.49s 
 
 99.000%   18.22s 
 
 99.900%   19.51s 
 
 99.990%   19.76s 
 
 99.999%   19.79s 
 
100.000%   19.79s 

  Detailed Percentile spectrum:
       Value   Percentile   TotalCount 1/(1-Percentile)

      0.121     0.000000            1         1.00
       0.908     0.100000        15012         1.11
       1.245     0.200000        29998         1.25
       1.517     0.300000        45001         1.43
       1.769     0.400000        60034         1.67
       2.026     0.500000        74985         2.00
       2.163     0.550000        82510         2.22
       2.303     0.600000        89997         2.50
       2.453     0.650000        97529         2.86
       2.613     0.700000       104957         3.33
       2.797     0.750000       112456         4.00
       2.897     0.775000       116241         4.44
       3.003     0.800000       119995         5.00
       3.121     0.825000       123748         5.71
       3.253     0.850000       127447         6.67
       3.413     0.875000       131202         8.00
       3.505     0.887500       133071         8.89
       3.617     0.900000       134960        10.00
       3.743     0.912500       136844        11.43
       3.899     0.925000       138705        13.33
       4.099     0.937500       140585        16.00
       4.227     0.943750       141513        17.78
       4.395     0.950000       142448        20.00
       4.623     0.956250       143378        22.86
       4.951     0.962500       144318        26.67
       5.423     0.968750       145253        32.00
       5.763     0.971875       145724        35.56
       6.151     0.975000       146188        40.00
       6.775     0.978125       146661        45.71
       7.443     0.981250       147127        53.33
       8.239     0.984375       147595        64.00
       8.671     0.985938       147832        71.11
       9.151     0.987500       148067        80.00
       9.711     0.989062       148300        91.43
      10.335     0.990625       148532       106.67
      11.143     0.992188       148766       128.00
      11.799     0.992969       148883       142.22
      12.367     0.993750       149001       160.00
      13.231     0.994531       149117       182.86
      14.031     0.995313       149234       213.33
      14.935     0.996094       149352       256.00
      15.455     0.996484       149409       284.44
      16.199     0.996875       149469       320.00
      16.847     0.997266       149527       365.71
      17.903     0.997656       149585       426.67
      19.039     0.998047       149644       512.00
      19.551     0.998242       149673       568.89
      20.287     0.998437       149702       640.00
      20.991     0.998633       149732       731.43
      21.775     0.998828       149761       853.33
      23.407     0.999023       149790      1024.00
      24.031     0.999121       149806      1137.78
      24.671     0.999219       149819      1280.00
      25.855     0.999316       149834      1462.86
      26.927     0.999414       149849      1706.67
      28.127     0.999512       149863      2048.00
      29.743     0.999561       149871      2275.56
      30.959     0.999609       149878      2560.00
      31.759     0.999658       149885      2925.71
      32.927     0.999707       149893      3413.33
      34.559     0.999756       149900      4096.00
      35.295     0.999780       149904      4551.11
      36.063     0.999805       149907      5120.00
      38.047     0.999829       149911      5851.43
      38.719     0.999854       149915      6826.67
      39.679     0.999878       149918      8192.00
      41.023     0.999890       149920      9102.22
      42.303     0.999902       149922     10240.00
      42.623     0.999915       149924     11702.86
      42.879     0.999927       149926     13653.33
      43.295     0.999939       149927     16384.00
      43.551     0.999945       149928     18204.44
      44.191     0.999951       149929     20480.00
      44.575     0.999957       149930     23405.71
      45.375     0.999963       149931     27306.67
      46.111     0.999969       149932     32768.00
      46.111     0.999973       149932     36408.89
      46.175     0.999976       149933     40960.00
      46.175     0.999979       149933     46811.43
      47.039     0.999982       149934     54613.33
      47.039     0.999985       149934     65536.00
      47.039     0.999986       149934     72817.78
      48.959     0.999988       149935     81920.00
      48.959     0.999989       149935     93622.86
      48.959     0.999991       149935    109226.67
      48.959     0.999992       149935    131072.00
      48.959     0.999993       149935    145635.56
      49.983     0.999994       149936    163840.00
      49.983     1.000000       149936          inf
   

  156273 requests in 1.00m, 14.67MB read
  
Requests/sec:   2604.49

Transfer/sec:    250.43KB


CPU Get
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/CPUGet.svg)

Как видно 77% уходит на метод MyHttpServerImpl.getValueByKey, метод SendResponse занимает 6% CPU.

Alloc Get
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/AllocGet.svg)

Как видно метод MyHttpServerImpl.getValueByKey выделяет около 92% памяти.

Lock Get
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/LOCKGet.svg)
При чтении из базы появляются блокировки, что связано с реализацией итератора, которая была по умолчанию в Dao.

#[Put-запросы]

Выполним запрос put с 16 соединениями и 4 потоками, используя следующую команду: wrk -t4 -c16 -d1m -s wrk/put.lua -R3000 --latency http://127.0.0.1:8080. Для формирования запроса мы используем скрипт на языке Lua.

wrk -t4 -c16 -d1m -s wrk/put.lua -R3000 --latency http://127.0.0.1:8080

Running 1m test @ http://127.0.0.1:8080
  4 threads and 16 connections

  
  Thread Stats   Avg      Stdev     Max   +/- Stdev
  
    Latency     2.33ms    1.92ms  49.95ms   93.23%
    
    Req/Sec   792.89    137.69     2.00k    85.34%
    
  Latency Distribution (HdrHistogram - Recorded Latency)
  
 50.000%    2.03ms
 
 75.000%    2.80ms
 
 90.000%    3.62ms
 
 99.000%   10.07ms
 
 99.900%   23.12ms
 
 99.990%   41.25ms
 
 99.999%   48.96ms
 
100.000%   49.98ms

  Detailed Percentile spectrum:
       Value   Percentile   TotalCount 1/(1-Percentile)

       0.121     0.000000            1         1.00
       0.908     0.100000        15012         1.11
       1.245     0.200000        29998         1.25
       1.517     0.300000        45001         1.43
       1.769     0.400000        60034         1.67
       2.026     0.500000        74985         2.00
       2.163     0.550000        82510         2.22
       2.303     0.600000        89997         2.50
       2.453     0.650000        97529         2.86
       2.613     0.700000       104957         3.33
       2.797     0.750000       112456         4.00
       2.897     0.775000       116241         4.44
       3.003     0.800000       119995         5.00
       3.121     0.825000       123748         5.71
       3.253     0.850000       127447         6.67
       3.413     0.875000       131202         8.00
       3.505     0.887500       133071         8.89
       3.617     0.900000       134960        10.00
       3.743     0.912500       136844        11.43
       3.899     0.925000       138705        13.33
       4.099     0.937500       140585        16.00
       4.227     0.943750       141513        17.78
       4.395     0.950000       142448        20.00
       4.623     0.956250       143378        22.86
       4.951     0.962500       144318        26.67
       5.423     0.968750       145253        32.00
       5.763     0.971875       145724        35.56
       6.151     0.975000       146188        40.00
       6.775     0.978125       146661        45.71
       7.443     0.981250       147127        53.33
       8.239     0.984375       147595        64.00
       8.671     0.985938       147832        71.11
       9.151     0.987500       148067        80.00
       9.711     0.989062       148300        91.43
      10.335     0.990625       148532       106.67
      11.143     0.992188       148766       128.00
      11.799     0.992969       148883       142.22
      12.367     0.993750       149001       160.00
      13.231     0.994531       149117       182.86
      14.031     0.995313       149234       213.33
      14.935     0.996094       149352       256.00
      15.455     0.996484       149409       284.44
      16.199     0.996875       149469       320.00
      16.847     0.997266       149527       365.71
      17.903     0.997656       149585       426.67
      19.039     0.998047       149644       512.00
      19.551     0.998242       149673       568.89
      20.287     0.998437       149702       640.00
      20.991     0.998633       149732       731.43
      21.775     0.998828       149761       853.33
      23.407     0.999023       149790      1024.00
      24.031     0.999121       149806      1137.78
      24.671     0.999219       149819      1280.00
      25.855     0.999316       149834      1462.86
      26.927     0.999414       149849      1706.67
      28.127     0.999512       149863      2048.00
      29.743     0.999561       149871      2275.56
      30.959     0.999609       149878      2560.00
      31.759     0.999658       149885      2925.71
      32.927     0.999707       149893      3413.33
      34.559     0.999756       149900      4096.00
      35.295     0.999780       149904      4551.11
      36.063     0.999805       149907      5120.00
      38.047     0.999829       149911      5851.43
      38.719     0.999854       149915      6826.67
      39.679     0.999878       149918      8192.00
      41.023     0.999890       149920      9102.22
      42.303     0.999902       149922     10240.00
      42.623     0.999915       149924     11702.86
      42.879     0.999927       149926     13653.33
      43.295     0.999939       149927     16384.00
      43.551     0.999945       149928     18204.44
      44.191     0.999951       149929     20480.00
      44.575     0.999957       149930     23405.71
      45.375     0.999963       149931     27306.67
      46.111     0.999969       149932     32768.00
      46.111     0.999973       149932     36408.89
      46.175     0.999976       149933     40960.00
      46.175     0.999979       149933     46811.43
      47.039     0.999982       149934     54613.33
      47.039     0.999985       149934     65536.00
      47.039     0.999986       149934     72817.78
      48.959     0.999988       149935     81920.00
      48.959     0.999989       149935     93622.86
      48.959     0.999991       149935    109226.67
      48.959     0.999992       149935    131072.00
      48.959     0.999993       149935    145635.56
      49.983     0.999994       149936    163840.00
      49.983     1.000000       149936          inf
      

  179980 requests in 1.00m, 15.96MB read
  
Requests/sec:   2999.58

Transfer/sec:    272.42KB

CPU Put
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/CPUPut.svg)
Метод MyHttpServerImpl.putValueByKey занимает около 77%, а на отправку запроса тратится 10%.

Alloc Put
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/AllocPut.svg)
Как видим, около 64% памяти выделяет MyHttpServerImpl.putValueByKey, 4,17% выделяет метод SendResponse.

Lock Put
![](https://github.com/Basta123/2020-highload-dht/blob/master/profiling/Stage2/LOCKPut.svg)

Как видим по картинке, put не блокируется, что связано с хорошей реализацией бд, поэтому профайлер нам ничего не выдал.