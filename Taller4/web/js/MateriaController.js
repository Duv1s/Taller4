'use strict';

module.controller('MateriaCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = [];
        $scope.datosFormulario = {horario:[]};
        $scope.datosHorario = {};
        $scope.items = [
               'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes'
                ];


        $scope.panelEditar = false;
        $scope.listar = function () {
            $http.get('./webresources/Materia', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarCarrera = function () {
            $http.get('./webresources/Carrera', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCarrera = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de carrera, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCarrera();
        $scope.listarProfesor = function () {
            $http.get('./webresources/Profesor', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaProfesor = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de profesor, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarProfesor();
        $scope.listarHorario = function () {
            $http.get('./webresources/Horario', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaHorario = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de horario, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarHorario();


        $scope.listar();
        //guardar
        $scope.nuevo = function () {
            $scope.panelEditar = true;
            $scope.datosFormulario = {horario:[]};
            $scope.datosHorario = [];
        };

        $scope.guardar = function () {
            $scope.errores = {};
            var error = false;

            if (error)
                return;
            $http.post('./webresources/Materia', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con Exito");
                $scope.panelEditar = false;
                $scope.listar();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.guardarHorario = function(){
             $scope.datosFormulario.horario.push($scope.datosHorario);
             $scope.datosHorario = {};
        };
        
        $scope.eliminarHorario = function(data){
            if (confirm('Desea eiminar este registro')) {
                var index = $scope.datosFormulario.horario.indexOf(data);
                $scope.datosFormulario.horario.splice(index,1);
            }
        };
        
         
        
        
        $scope.cancelar = function () {
            $scope.panelEditar = false;
            $scope.datosFormulario = {};
        };

        //editar
        $scope.editar = function (data) {
            $scope.panelEditar = true;
            $scope.datosFormulario = data;
        };
        
        
        //eliminar
        $scope.eliminar = function (data) {
            if (confirm('\xbfDesea elminar este registro?')) {
                $http.delete('./webresources/Materia/' + data.id, {})
                        .success(function (data, status, headers, config) {
                            $scope.listar();
                        }).error(function (data, status, headers, config) {
                    alert('Error al eliminar la informaci\xf3n de Materia, por favor intente m\xe1s tarde');
                });
            }
        };
    }]);

