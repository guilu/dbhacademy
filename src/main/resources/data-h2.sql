Insert into BDD_FIREFIGHT.TB01_AGRUPACION(ID,CODIGO,TEXTO) values (1,'AG001','LEGISLACIÓN');


Insert into BDD_FIREFIGHT.TB02_TEMA(ID,CODIGO,TEXTO,AGRUPACION_ID) values (1,'TM0001','Estatuto de Autonomía de la CAM',1);

Insert into BDD_FIREFIGHT.TB03_PREGUNTA(ID,CODIGO,TEXTO,TEMA_ID) values (1,'PR0001','Sólo uno de los siguientes principios está garantizado constitucionalmente:',1);

Insert into BDD_FIREFIGHT.TB04_RESPUESTA(ID,CODIGO,TEXTO,PREGUNTA_ID) values (1,'RE0001','La interdicción de la arbitrariedad del poder judicial.',1);
Insert into BDD_FIREFIGHT.TB04_RESPUESTA(ID,CODIGO,TEXTO,PREGUNTA_ID) values (2,'RE0002','La responsabilidad e interdicción de la arbitrariedad de la Administración Pública, tanto nacional, como autonómica o local',1);
Insert into BDD_FIREFIGHT.TB04_RESPUESTA(ID,CODIGO,TEXTO,PREGUNTA_ID) values (3,'RE0003','La responsabilidad de la Administración Pública.',1);
Insert into BDD_FIREFIGHT.TB04_RESPUESTA(ID,CODIGO,TEXTO,PREGUNTA_ID) values (4,'RE0004','La responsabilidad e interdicción de la arbitrariedad de los poderes públicos.',1);
