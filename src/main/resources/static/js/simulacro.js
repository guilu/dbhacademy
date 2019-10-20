var Simulacro = (function () {

    //modo estricto
    "use strict";
    //elementos del Test

    var tiempoTotalResolucion,tiempoResolucionPregunta;

    return {

        resolver: function() {
                //primero todos los radios disabled
                $('input[type=radio]').prop('disabled',true);

                //resolviendo
                var txtAciertos = $('#txtAciertos');
                var txtErrores = $('#txtErrores');

                var ok = false;

                var soluciones = $('.solucion');
                soluciones.each(function () {
                    var solucion = $(this);
                    var respuesta = solucion.find('.respuesta');

                    if (respuesta.hasClass('c')) {
                        respuesta.addClass('fa fa-check-circle font-color-green');
                        var check = $('#r_' + respuesta.prop('id'));
                        if (check.prop('checked') == true) {
                            var currentAciertos = parseInt(aciertos.text()) + 1;
                            aciertos.text(currentAciertos);
                            txtAciertos.val(currentAciertos);
                            ok = true;
                        }
                    } else {
                        respuesta.addClass('fa fa-times-circle font-color-red')
                    }
                    solucion.show();
                });
        }
    }

}());