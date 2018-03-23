'use strict';

module.controller('EstudianteCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = [];
        $scope.datosFormulario = {};
        $scope.panelEditar = false;
        $scope.listar = function () {
            $http.get('./webresources/Estudiante', {})
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
        $scope.listarLugarNacimiento = function () {
            $http.get('./webresources/Municipio', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaLugarNacimiento = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de lugarNacimiento, por favor intente m\xe1s tarde');
            });
        };
        $scope.buscar = function () {

            if (($scope.datosFormulario.nombreSearch !== undefined && $scope.datosFormulario.nombreSearch !== "") || ($scope.datosFormulario.apellidoSearch !== undefined && $scope.datosFormulario.apellidoSearch !== "")) {
                console.log($scope.datosFormulario.nombreSearch);
                $http.get('./webresources/Estudiante/buscar?nombre=' + $scope.datosFormulario.nombreSearch + '&apellido=' + $scope.datosFormulario.apellidoSearch, {})
                        .success(function (data, status, headers, config) {
                            $scope.lista = data;
                        }).error(function (data, status, headers, config) {
                    alert('Error al consultar la informaci\xf3n de ' + $scope.datosFormulario.nombreSearch + "\n " + $scope.datosFormulario.apellidoSearch);
                });
            } else {
                $scope.listar();
            }
        };
        $scope.listarLugarNacimiento();


        $scope.listar();
        //guardar
        $scope.nuevo = function () {
            $scope.panelEditar = true;
            $scope.datosFormulario = {};
        };

        $scope.guardar = function () {
            $scope.errores = {};
            var error = false;

            if (error)
                return;


            $scope.datosFormulario.fechaNacimiento = $('#fechaNacimiento').val();
            $http.post('./webresources/Estudiante', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con Exito");
                $scope.panelEditar = false;
                $scope.listar();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.cancelar = function () {
            $scope.panelEditar = false;
            $scope.datosFormulario = {};
        };

        //editar
        $scope.editar = function (data) {
            $scope.panelEditar = true;
            $scope.datosFormulario = data;
            $scope.datosFormulario.fechaNacimiento = data.fechaNacimiento;
        };
        //eliminar
        $scope.eliminar = function (data) {
            if (confirm('\xbfDesea elminar este registro?')) {
                $http.delete('./webresources/Estudiante/' + data.id, {})
                        .success(function (data, status, headers, config) {
                            $scope.listar();
                        }).error(function (data, status, headers, config) {
                    alert('Error al eliminar la informaci\xf3n de Estudiante, por favor intente m\xe1s tarde');
                });
            }
        };
    }]);
