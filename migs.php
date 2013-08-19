    $SECURE_SECRET =  $signature; //value from migs payment gateway
        $accessCode    =  $accesscode;//value from migs payment gateway
        $merchantId    =  $merchantid;//value from migs payment gateway
        $paymentdata = array(
                 "vpc_AccessCode" => $accessCode,
                 "vpc_Amount" => ($amount*100),//our product price , must multipy by 100
                 "vpc_Command" => 'pay',
                 "vpc_Locale" => 'en',// order id
                 "vpc_MerchTxnRef" => random_unique_value(like session),
                 "vpc_Merchant" => $merchantId,
                 "vpc_OrderInfo" => "Some Comment",
                 "vpc_ReturnURL" => "htps://yoursite.com/returnpoint",//here code for db updation, return variable here
                 "vpc_Version" => '1'
                           );

        $actionurl = 'https://migs.mastercard.com.au/vpcpay' . "?";
        $HashData = $SECURE_SECRET;
        $str = 0;
        foreach ($paymentdata as $key => $value) {
            // create the md5 input and URL
            if (strlen($value) > 0) {
                // this ensures the first paramter of the URL is preceded by the '?' char
                if ($appendAmp == 0) {
                    $actionurl .= urlencode($key) . '=' . urlencode($value);
                    $str = 1;
                } else {
                    $actionurl .= '&' . urlencode($key) . "=" . urlencode($value);
                }
                $HashData .= $value;
            }
        }

        if (strlen($SECURE_SECRET) > 0){$actionurl .= "&vpc_SecureHash=" . strtoupper(md5($HashData));}
        header("Location: " . $actionurl);
    