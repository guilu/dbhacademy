var Simulacro = (function () {

    //modo estricto
    "use strict";
    //elementos del Test

    var aciertos,errores,blancos;
    var reloj,intervalo, end;

    return {
        setReloj: function(elReloj) {
            reloj = elReloj;
        },
        setEnd: function (theEnd){
            end = theEnd;
        },
        cuentaAtras: function(elReloj, milisegundos) {
            end = new Date().getTime() + milisegundos;
            reloj = elReloj;

            intervalo = setInterval(this.showRemaining, 1000);
        },
        showRemaining: function(){
            var now = new Date();
            var distance = end - now;
            if (distance < 0) {
                this.resolver();
            }
            var _second = 1000;
            var _minute = _second * 60;
            var _hour = _minute * 60;
            var _day = _hour * 24;

            // Time calculations for days, hours, minutes and seconds
            //var days = Math.floor(distance / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);

            hours = hours < 10 ? "0" + hours : hours;
            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            reloj.text(hours + ":" + minutes + ":" + seconds );
            $('#menu-reloj').show();
        },
        resolver: function() {
                clearInterval(intervalo);
                console.log('resolviendo');
                //primero todos los radios disabled
                $('input[type=radio]').prop('disabled',true);
                $('.form-check').addClass('disabled');

                //resolviendo
                aciertos = 0;
                errores = 0;
                blancos = 0;

                var okMarcado = false;
                var errorMarcado = false;

                var ok = false;
                //cada pregunta coger las soluciones
                var preguntas = $('.pregunta-body');
                //para cada pregunta contar si es acierto fallo o blanco....
                preguntas.each( function () {
                    var soluciones = $(this).find('.solucion');
                    var algunaMarcada = false;
                    soluciones.each(function () {
                        var solucion = $(this);
                        var respuesta = solucion.find('.respuesta');
                        var check = $('#r_' + respuesta.prop('id'));
                        var checked = check.prop('checked');
                        if (respuesta.hasClass('c')) {
                            respuesta.addClass('fa fa-check-circle font-color-green');
                            if (checked) {
                                aciertos++;
                                algunaMarcada = true
                            }
                        } else {
                            respuesta.addClass('fa fa-times-circle font-color-red')
                            if (checked) {
                                errores++;
                                algunaMarcada = true
                            }
                        }
                        solucion.show();
                    });
                    if (!algunaMarcada) {
                        blancos++;
                    }
                });


            var nota;
            if( aciertos < (errores/3) ) {
                nota = 0;
            } else {
                nota = ((aciertos - (errores/3)) * 10 )/ $('#numPreguntasTotales').val();
            }

            //Porcentaje de acierto de lo contestado (se entiende)....
            var porcentaje = parseFloat( (aciertos / (aciertos + errores) * 100)).toFixed(2);
            if ((aciertos == 0) && (errores == 0)) {
            	porcentaje = 0;
            }


            var notaFinal = parseFloat(nota).toFixed(2);
            $('#notaFinal').text(notaFinal);
            $('#txtResultado').text(aciertos + ' aciertos ' + errores + ' errores. ' + blancos + ' en blanco.');
            $('#txtPorcentaje').text('Porcentaje de acierto: ' + porcentaje + '%');
            $('#porcentajeAciertos').css('width', porcentaje + '%');
            $('#mdlResultado').modal('show');

            $('#button-corregir-simulacro').hide();
            $('#button-finalizar-simulacro').show();

        }
    }

}());