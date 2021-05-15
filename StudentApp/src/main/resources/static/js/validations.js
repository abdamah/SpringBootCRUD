        $(document).ready(function () {
            //.Hide span errors
            $("#nameError").hide();
            $("#genderError").hide();
            $("#courseError").hide();
            $("#addressError").hide();

            //2. Define flag variables
            var nameError = false;
            var genderError = false;
            var courseError = false;
            var addressError = false;

            //3. Link events 
            $("#name").keyup(function () {
                //5. class  function
                validate_name();
            });

            $('input[type="radio"][name="gender"]').change(function () {
                //call function
                validate_gender();
            })

            $("#course").change(function () {
                //5. class  function
                validate_course();
            });
            $("#address").keyup(function () {
                //5. class  function
                validate_address();
            });

            //4. Define the function
            function validate_name() {
                var val = $("#name").val();
                if (val == '') {
                    $("#nameError").html("<b>Name is Required.<b>");
                    $("#nameError").css("color", "red");
                    $("#nameError").show();

                    nameError = false;
                } else {
                    $("#nameError").hide();
                    nameError = true;
                }
            }

            function validate_gender() {
                var len = $('input[type="radio"][name="gender"]:checked').length;
                if (len == 0) {
                    $("#genderError").html("<b>Gender is Required.<b>");
                    $("#genderError").css("color", "red");
                    $("#genderError").show();

                    genderError = false;
                } else {
                    $("#genderError").hide();
                    genderError = true;
                }
            }


            function validate_course() {
                var val = $("#course").val();
                if (val == '') {
                    $("#courseError").html("<b>Course is Required.<b>");
                    $("#courseError").css("color", "red");
                    $("#courseError").show();

                    courseError = false;
                } else {
                    $("#courseError").hide();
                    courseError = true;
                }
            }
            function validate_address() {
                var val = $("#address").val();
                if (val == '') {
                    $("#addressError").html("<b>Address is Required.<b>");
                    $("#addressError").css("color", "red");
                    $("#addressError").show();

                    addressError = false;
                } else {
                    $("#addressError").hide();
                    addressError = true;
                }
            }


            //form submittion
            $("#std").submit(function () {
                validate_name();
                validate_gender();
                validate_course();
                validate_address();

                if (nameError && genderError && courseError && addressError) {
                    return true;
                }
                else {
                    return false;
                }
            });

        });
