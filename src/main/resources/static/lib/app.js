var app = angular.module('mailsender', []);

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

app.service('mailSenderService', [ '$http', '$rootScope', function($http, $rootScope) {
	this.search = function(name, date) {
		$http.get("http://localhost:8080/mail-sender", {
			
		}).success(function(response) {
			$rootScope.metadataList = response;
		}).error(function() {
		});
	}
}]);

app.service('fileUpload', ['$http','mailSenderService', function($http, ArchiveService) {
	this.uploadFileToUrl = function(uploadUrl, file) {
		var fd = new FormData();
		fd.append('file', file);
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function() {
			alert("E-mail enviado com sucesso!");
		}).error(function() {
		});
	}
} ]);

app.controller('UploadCtrl', [ '$scope', 'fileUpload',
		function($scope, fileUpload) {
			$scope.uploadFile = function() {
				var file = $scope.myFile;
				console.log('file is ' + JSON.stringify(file));
				var uploadUrl = "/mail-sender/send";
				fileUpload.uploadFileToUrl(uploadUrl, file);
			};
} ]);

