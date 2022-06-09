insert into employee.documents(name, bytes, date_format)
values ('test.csv', convert_to('143,10,5/5/2022,12/5/2022
218,10,3/6/2022,3/6/2022
200,10,3/5/2022,15/5/2022
200,12,3/5/2022,NULL
143,12,5/5/2022,NULL', 'LATIN1'), 'd/M/yyyy');
