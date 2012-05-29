String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) { 
    return typeof args[number] != 'undefined' ? args[number] : match;
  });
};

(function() {
  
  var blankFieldError = 'You must enter {0}.';
  var naiveEmailRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  var minimumPasswordLength = 6;
  
  function showError(message) {
    $('#error-message').show().html(message);
  }
  
  function hideErrors() {
    $('#error-message').hide();
  }
  
  $(document).ready(function() {
    var $formFields = {
      firstName: {
        element: $("input[name='first-name']"),
        validate: function() {
          var input = this.element.val();
          if (input.length == 0) {
            showError(blankFieldError.format("your first name"));
            return false;
          }
          return true;
        }
      },
      lastName: {
        element: $("input[name='last-name']"),
        validate: function() {
          var input = this.element.val();
          if (input.length == 0) {
            showError(blankFieldError.format("your last name"));
            return false;
          }
          return true;
        }
      },
      email: {
        element: $("input[name='email']"),
        validate: function() {
          var email = this.element.val();
          if (email.length == 0) {
            showError(blankFieldError.format("an email address"));
            return false;
          }
          else if (!naiveEmailRegex.test(email)) {
            showError("You must enter a valid email address.");
            return false;
          }
          return true;
        }
      },
      emailConfirm: {
        element: $("input[name='email-confirm']"),
        validate: function() {
          var email = this.element.val();
          if (email != $formFields.email.element.val()) {
            showError("Your emails do not match. Please try again.");
            return false;
          }
          return true;
        }
      },
      password: {
        element: $("input[name='password']"),
        validate: function() {
          var password = this.element.val();
          if (password.length < minimumPasswordLength) {
            showError("Your password must be at least {0} characters.".format(minimumPasswordLength));
            return false;
          }
          return true;
        }
      },
      validate: function() {
        hideErrors();
        for (var property in $formFields) {
          if ($formFields.hasOwnProperty(property) && $formFields[property] !== 'validate') {
            if (!this[property].validate()) {
              return false;
            }
          }
        }
        return true;
      }
    };
    
    $("form").submit(function(event) {
      event.preventDefault();
      return $formFields.validate();
    });
  });
  
}());

